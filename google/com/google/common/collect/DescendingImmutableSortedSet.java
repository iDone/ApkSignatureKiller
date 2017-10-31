

package com.google.common.collect;

import android.support.annotation.Nullable;

import com.google.common.annotations.GwtIncompatible;


class DescendingImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    private final ImmutableSortedSet<E> forward;

    DescendingImmutableSortedSet(ImmutableSortedSet<E> forward) {
        super(Ordering.from(forward.comparator()).reverse());
        this.forward = forward;
    }

    @Override
    public boolean contains(@Nullable Object object) {
        return forward.contains(object);
    }

    @Override
    public int size() {
        return forward.size();
    }

    @Override
    public UnmodifiableIterator<E> iterator() {
        return forward.descendingIterator();
    }

    @Override
    ImmutableSortedSet<E> headSetImpl(E toElement, boolean inclusive) {
        return forward.tailSet(toElement, inclusive).descendingSet();
    }

    @Override
    ImmutableSortedSet<E> subSetImpl(
            E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return forward.subSet(toElement, toInclusive, fromElement, fromInclusive).descendingSet();
    }

    @Override
    ImmutableSortedSet<E> tailSetImpl(E fromElement, boolean inclusive) {
        return forward.headSet(fromElement, inclusive).descendingSet();
    }

    @Override
    @GwtIncompatible("NavigableSet")
    public ImmutableSortedSet<E> descendingSet() {
        return forward;
    }

    @Override
    @GwtIncompatible("NavigableSet")
    public UnmodifiableIterator<E> descendingIterator() {
        return forward.iterator();
    }

    @Override
    @GwtIncompatible("NavigableSet")
    ImmutableSortedSet<E> createDescendingSet() {
        throw new AssertionError("should never be called");
    }

    @Override
    public E lower(E element) {
        return forward.higher(element);
    }

    @Override
    public E floor(E element) {
        return forward.ceiling(element);
    }

    @Override
    public E ceiling(E element) {
        return forward.floor(element);
    }

    @Override
    public E higher(E element) {
        return forward.lower(element);
    }

    @Override
    int indexOf(@Nullable Object target) {
        int index = forward.indexOf(target);
        if (index == -1) {
            return index;
        } else {
            return size() - 1 - index;
        }
    }

    @Override
    boolean isPartialView() {
        return forward.isPartialView();
    }
}
